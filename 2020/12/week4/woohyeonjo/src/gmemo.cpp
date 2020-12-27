#define null 0
typedef struct { int y, x; } AXIS;

struct Memo {
	int sy, sx, ey, ex, h, w, mid; 
	char txt[50][51];
	Memo *prev, *next;
	void reset(int _mid) {
		mid = _mid;         // for debugging
		prev = next = null;
	}
	void change_loc(int _sy, int _sx, int _h, int _w) {
		sy = _sy, sx = _sx, h = _h, w = _w;
		ey = sy + h - 1;
		ex = sx + w - 1;
	}
	void set_text(char str[]) {
		int i, j, k = 0;
		for (i = 0; i < h; i++) {
			for (j = 0; j < w; j++) 
				if (str[k] != null) txt[i][j] = str[k++];
				else 			    txt[i][j] = ' '; // 메모 빈 부분
			txt[i][j] = '\0';  // end of line : for debugging
		}
	}
	void move_on_top(Memo *_top) {
		if (prev == _top)         // 내 메모가 맨 위면 옮길 필요가 없다.
			return;
		else if (prev != null) {  // LinkedList에서 내 아래/위 메모를 서로 잇는다.
			next->prev = prev;    // 아래 메모가 내 위 메모를 가리킨다.
			prev->next = next;    // 위 메모가 내 아래 메모를 가리킨다.
		}
		next = _top->next;        // 내 메모를 top과 기존 맨 위 메모사이에 둔다.
		prev = _top;
		_top->next->prev = this;  // 기존 맨 위 메모와 top이 나를 가리키도록 한다.
		_top->next = this;
	}
};
Memo memo[10000], top, bottom;

inline int Min(int a, int b) { return a < b ? a : b; }
inline int Max(int a, int b) { return a > b ? a : b; }

void init(int mScreenSize) {
	top.next = &bottom;
	bottom.prev = &top;
}

void create_memo(int mId, int mY, int mX, int mHeight, int mWidth, char str[]) {
	memo[mId].reset(mId);
	memo[mId].change_loc(mY, mX, mHeight, mWidth);
	memo[mId].set_text(str);
	memo[mId].move_on_top(&top);
}

AXIS select_memo(int mId) {
	memo[mId].move_on_top(&top);
	AXIS ret;
	ret.y = memo[mId].sy;  ret.x = memo[mId].sx;
	return ret;
}

void move_memo(int mId, int mY, int mX) {
	memo[mId].change_loc(mY, mX, memo[mId].h, memo[mId].w);
	memo[mId].move_on_top(&top);
}

void modify_memo(int mId, int mHeight, int mWidth, char str[]) {
	memo[mId].change_loc(memo[mId].sy, memo[mId].sx, mHeight, mWidth);
	memo[mId].set_text(str);
	memo[mId].move_on_top(&top);
}

void get_screen_context(int mY, int mX, char res[5][5]) {
	for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) {
		res[i][j] = '\0';  // 초기화
	}
	int remain = 25;

	for (Memo* p = top.next; p != &bottom && remain > 0; p = p->next) {
		if (p->ey < mY || mY + 4 < p->sy ||
			p->ex < mX || mX + 4 < p->sx)   continue;

		int y1 = Max(mY, p->sy);
		int x1 = Max(mX, p->sx);
		int y2 = Min(mY + 4, p->ey);
		int x2 = Min(mX + 4, p->ex);

		for (int i = y1; i <= y2; i++) for (int j = x1; j <= x2; j++) {
			if (res[i - mY][j - mX] == '\0') {
				res[i - mY][j - mX] = p->txt[i - p->sy][j - p->sx];  // copy 'a'~'z' or  ' '
				remain--;
			}
		}
	}
	for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) {
		if (res[i][j] == ' ') res[i][j] = '\0';  // 메모 빈 부분도 '\0' 로 저장한다.
	}
}


