#define MAX_SIZE 1000000
#define MAX_NAME_LEN 10

typedef unsigned long long ull;

struct Node {
	int idx;
	ull name;

	bool operator<(Node& o) {
		if (name == o.name) {
			return idx < o.idx;
		}
		return name > o.name;
	}
} m[MAX_SIZE];

int midx;

ull MAX_SIZE_VALUE;

Node* malloc() { return &m[midx++]; }
ull toHash(const char* name) {
	ull code = 0, x = MAX_SIZE_VALUE;
	for (int i = 0; name[i]; ++i) {
		code += x * (name[i] - 'a' + 1);
		x /= 27;
	}
	return code;
}

struct Heap {
	Node* heap[MAX_SIZE];
	int heapSize = 0;

	void heapInit() {
		heapSize = 0;
	}

	int heapPush(int idx, char* name) {
		if (heapSize + 1 > MAX_SIZE) return 0;

		heap[heapSize] = malloc();
		heap[heapSize]->idx = idx;
		heap[heapSize]->name = toHash(name);

		int current = heapSize;
		while (current > 0 && *heap[current] < *heap[(current - 1) / 2]) {
			Node* temp = heap[(current - 1) / 2];
			heap[(current - 1) / 2] = heap[current];
			heap[current] = temp;
			current = (current - 1) / 2;
		}

		heapSize = heapSize + 1;

		return 1;
	}

	int heapPop() {
		if (heapSize <= 0) return 0;

		int res = heap[0]->idx;
		heapSize = heapSize - 1;

		heap[0] = heap[heapSize];

		int current = 0;
		while (current * 2 + 1 < heapSize) {
			int child;

			if (current * 2 + 2 == heapSize) {
				child = current * 2 + 1;
			}
			else {
				child = *heap[current * 2 + 1] < *heap[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
			}

			if (*heap[current] < *heap[child]) break;

			Node* temp = heap[current];
			heap[current] = heap[child];
			heap[child] = temp;

			current = child;
		}

		return res;
	}
} h;


void init() {

	MAX_SIZE_VALUE = 1;
	for (int i = 0; i < MAX_NAME_LEN - 1; ++i) MAX_SIZE_VALUE *= 27;

	h.heapInit();
}

void clear() {
	h.heapInit();
}

void push(int idx, char* name) {
	h.heapPush(idx, name);
}

int pop() {
	return h.heapPop();
}

int top() {
	return h.heap[0]->idx;
}

