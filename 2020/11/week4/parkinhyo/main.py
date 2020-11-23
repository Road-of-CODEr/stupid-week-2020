import csv
from collections import defaultdict, Counter
f = open('data.csv', 'r', encoding="utf-8")

contents = defaultdict(int)
for line in csv.reader(f):
    contents[line[1]] += 1

print('출제 빈도 : 내용')
for content in Counter(contents).most_common():
    print(f'    {content[1]}       {content[0]}')

"""
출제 빈도 : 내용
    9       fdiskfstab
    9       httpd
    8       iptables
    6       module
    6       rsyslog
    6       sendmail
    6       named
    5       nfs
    5       rpm
    4       chmod
    4       logrotate
    4       vsftpd
    4       dhcpd
    4       lastb
    4       last
    3       sshd_config
    3       cpio
    3       smb
    3       squid
    3       crontab
    3       chattr
    3       proc
    3       renice
    3       tcp wrapper
    3       /var/log/wtmp
    2       pgrep
    2       tar
    2       sysctl
    2       dump&restore
    2       passwd
    2       yum
    2       make
    2       /var/log
    2       lsattr
    2       nis
    2       xinetd
    2       usermod
    2       disk quota
    2       nice
    2       gcc
    2       lastlog
    2       dd(data dumper)
    2       blkid
    2       /var/log/btmp
    2       lp
    2       rsync
    2       mke2fs
    1       /etc/skel
    1       pkill
    1       renice
    1       ssh-keygen
    1       grub
    1       nmap
    1       netstat
    1       ln
    1       ps
    1       which&whereis
    1       ldd
    1       lostb
    1       /etc/login.defs
    1       blokid
    1       blockdev
    1       chage
    1       signal
    1       logger
    1       telnet
    1       useradd
    1       fdiskfstab
    1       mkdir
    1       visudo
    1       whoami
    1       who am i
    1       id
    1       chown

"""