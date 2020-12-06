// Text editor

const int MAX = 700;

struct Line {
    int len;
    char str[MAX];
    Line * next;
    Line * prev;
};

Line pool[MAX];
Line * head;
Line * curLine;
int cntR;
int curC;


void init(int n){
    // TO DO
    cntR = 0;
    head = &pool[cntR++];
    head->prev = nullptr;
    head->next = nullptr;
    head->len = 0;

    curLine = head;
    curC = 0;
}

void input_char(char in_char){
    // TO DO
    if(curC < curLine->len) {
        for(register int i = curLine->len ; i > curC ; --i) {
            curLine->str[i] = curLine->str[i - 1];
        }
    }

    curLine->str[curC++] = in_char;
    curLine->len++;
}

void input_newline(){
    // TO DO
    Line * newLine = &pool[cntR++];
    Line * temp = curLine->next;

    curLine->next = newLine;
    newLine->prev = curLine;
    newLine->next = temp;
    newLine->len = 0;

    if(temp) temp->prev = newLine;

    if(curC < curLine->len) {
        register int i;
        for(i = 0 ; curC + i < curLine->len ; ++i) {
            newLine->str[i] = curLine->str[curC + i];
        }

        curLine->len = curC;
        newLine->len = i;
    }

    curLine = newLine;
    curC = 0;
}

void move_cursor(int direction){ // 0: Up, 1: Down, 2: Left, 3: Right
    // TO DO
    switch(direction) {
        case 0:
            if(curLine->prev) {
                curLine = curLine->prev;
                if(curLine->len < curC) {
                    curC = curLine->len;
                }
            }
            break;
        case 1:
            if(curLine->next) {
                curLine = curLine->next;
                if(curLine->len < curC) {
                    curC = curLine->len;
                }
            }
            break;
        case 2:
            if(curC > 0) {
                curC--;
            } else {
                if(curLine->prev) {
                    curLine = curLine->prev;
                    curC = curLine->len;
                }
            }
            break;
        case 3:
            if(curC < curLine->len) {
                curC++;
            } else {
                if(curLine->next) {
                    curLine = curLine->next;
                    curC = 0;
                }
            }
            break;
    }
}

char get_char(int row, int column){
    register Line * line = head;
    for(register int i = 0 ; i < row - 1 ; ++i) {
        line = line->next;
    }

    return line->str[column - 1];
}