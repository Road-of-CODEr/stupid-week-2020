#define INPUT_MAX 100001
#define BUCKET_MAX 1001
#define DIVIDER 1000
#define nullptr 0

struct Node {
	Node* next;
	int data;
};

Node memory[INPUT_MAX];
Node head[BUCKET_MAX];
int midx;

Node* malloc() {
	return &memory[midx++];
}

void init() {
	for (register int i = 0; i < INPUT_MAX; ++i) {
		memory[i].next = nullptr;
	}

	for (register int i = 0; i < BUCKET_MAX; ++i) {
		head[i].next = nullptr;
		head[i].data = 0;
	}

	midx = 0;
}

void insert(int data) {
	int key = data / DIVIDER;

	register Node* newNode = malloc();
	register Node* p = &head[key];

	newNode->data = data;
	p->data++;

	while (p->next && data > p->next->data) p = p->next;

	newNode->next = p->next;
	p->next = newNode;
}

int remove(int index) {
	int cnt = 0;
	int key = 0;
	int res = 0;

	while (index >= cnt + head[key].data) cnt += head[key++].data;

	register Node* p = &head[key];
	p->data--;

	index -= cnt;
	while (index--) p = p->next;

	res = p->next->data;
	p->next = p->next->next;
	return res;
}

int searchByIndex(int index) {
	int cnt = 0;
	int key = 0;
	int res = 0;

	while (index >= cnt + head[key].data) cnt += head[key++].data;

	register Node* p = &head[key];

	index -= cnt;
	while (index--) p = p->next;

	res = p->next->data;
	return res;
}
