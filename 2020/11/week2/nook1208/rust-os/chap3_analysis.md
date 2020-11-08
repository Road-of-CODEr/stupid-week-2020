
```
unsafe fn kernel_init() -> ! {
        println!("[0] Hello from Rust!");
            println!("[1] eomeom!");

                panic!("Stopping here.")
                
}
```
-> 
```
/// Prints with a newline.d
///
/// Carbon copy from https://doc.rust-lang.org/src/std/macros.rs.html
#[macro_export]
macro_rules! println {
        () => ($crate::print!("\n"));
        ($($arg:tt)*) => ({
                    $crate::print::_print(format_args_nl!($($arg)*));
                        
                })
        
}
```
여기에서 format_args_nl 는 format_args  에서 newline 만 추가로 출력해주는 부분.
이 매크로는 파라미터로 넘어오는 string 들을 출력 가능한 아규먼트로 만들어준다. 그래서 리턴 값이 fmt::Arguments 이다
format_args 는 포맷 아규먼트들을 안전하게 만들어주기 위해서 필요한 부분이라고 한다. 
그러므로 저 매크로를 사용해서 포맷 아규먼트들을 안전하게 만들고 그 아규먼트들을 다시 write! 와 같은 매크로로 넘김으로써 
최종적으로 로그 출력이 가능하게 된다.
->
```
#[doc(hidden)]
pub fn _print(args: fmt::Arguments) {
        use console::interface::Write;

            bsp::console::console().write_fmt(args).unwrap();
            
}
```
write_fmt 를 쓰려면 fmt::write 트레잇을 구현해야하는데 이때 요구되는 구현이 write_str 함수이다.
이건 write! 매크로나 write_fmt 함수를 쓸 때 사용되며 두 경우 모두 최종적으로 사용자가 구현한 write_str 함수를 통해 특정 주소에 포맷팅 된 문자열을 작성한다.
write! 매크로는 그냥 write_fmt 의 wrapper 매크로이다. 
또한 write_fmt 는 write 함수를 부르며, 이 함수는 아래와 같이 write_str 함수를 호출하여 동작함을 알 수 있다.
-> 
https://doc.rust-lang.org/std/fmt/fn.write.html
```
#[stable(feature = "rust1", since = "1.0.0")]
pub fn write(output: &mut dyn Write, args: Arguments<'_>) -> Result {
    let mut formatter = Formatter {
                flags: 0,
                        width: None,
                                precision: None,
                                        buf: output,
                                                align: rt::v1::Alignment::Unknown,
                                                        fill: ' ',
                                                            
    };

        let mut idx = 0;

        match args.fmt {
            None => {
                            // We can use default formatting parameters for all arguments.
                for (arg, piece) in args.args.iter().zip(args.pieces.iter()) {
                                    formatter.buf.write_str(*piece)?;
                                                    (arg.formatter)(arg.value, &mut formatter)?;
                                                                    idx += 1;
                                                                                
                }
                        
            }
            Some(fmt) => {
                            // Every spec has a corresponding argument that is preceded by
                                        // a string piece.
                for (arg, piece) in fmt.iter().zip(args.pieces.iter()) {
                                    formatter.buf.write_str(*piece)?;
                                                    run(&mut formatter, arg, &args.args)?;
                                                                    idx += 1;
                                                                                
                }
                        
            }
                
        }

            // There can be only one trailing string piece left.
        if let Some(piece) = args.pieces.get(idx) {
                    formatter.buf.write_str(*piece)?;
                        
        }

            Ok(())
            
}
```

결국 마지막엔 println! 을 위해 구현되는 건 write_str 함수이므로 그 함수를 분석해보자.

```
impl fmt::Write for QEMUOutput {
    fn write_str(&mut self, s: &str) -> fmt::Result {
        for c in s.chars() {
            unsafe {
                                core::ptr::write_volatile(0x3F20_1000 as *mut u8, c as u8);
                                            
            }
                    
        }

                Ok(())
                    
    }
    
}
```

코드로만 보면 write_volatile 에서 0x3F20_1000 주소영역에 1바이트씩 계속 overwrite 할 것 처럼 보이는데 그게 아닌가?
그리고 저 주소는 qemu 의 stdout 주소인가? 

```
```
```')
```
```
```
```
```
```
```
