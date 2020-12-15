#define MAXServer 10
#define MAXUser 10000
#define NULL 0
#define MIN(a, b) (a < b ? (a) : (b))
#define MAX(a, b) (a < b ? (b) : (a))

struct Distance {
    int sid;
    int len;
};

struct Node {
    int uid;
    int priority;
    Node* prev;
    Node* next;
};

Node* Head = NULL;
int ServerAxis[MAXServer];
int ServerCnt[MAXServer];
int Length, NumServer, Capacity;
int UserSid[MAXUser];
Distance UserDistance[MAXUser][MAXServer];
Node* UserNode[MAXUser];

void insert(int uid, int priority) {
    Node* newNode = new Node;
    newNode->uid = uid;
    newNode->priority = priority;
    newNode->prev = newNode->next = NULL;
    UserNode[uid] = newNode;

    if (Head == NULL) {
        Head = newNode;
        return;
    }

    Node* curr = Head;
    while (curr) {
        if (priority > curr->priority || (priority == curr->priority && uid < curr->uid)) {
            Node* temp = curr->prev;
            curr->prev = newNode;
            newNode->prev = temp;
            newNode->next = curr;
            if (temp)
                temp->next = newNode;
            else
                Head = newNode;

            return;
        }

        if (curr->next == NULL) {
            curr->next = newNode;
            newNode->prev = curr;
            return;
        }
        curr = curr->next;
    }
}

void remove(Node* node) {
    Node* left = node->prev;
    Node* right = node->next;
    if (left)
        left->next = right;
    else
        Head = right;

    if (right)
        right->prev = left;

    delete node;
}

void traversal() {
    Node* node = Head;
    while (node) {
        for (int i = 0; i < NumServer; ++i) {
            int sid = UserDistance[node->uid][i].sid;
            if (ServerCnt[sid] < Capacity) {
                UserSid[node->uid] = sid;
                ServerCnt[sid]++;
                break;
            }
        }
        node = node->next;
    }
}

//////////////////////////////////////////////////////////////
void init(int L, int N, int C, int axis[MAXServer]) {
    Node* curr = Head;
    while (curr) {
        Node* temp = curr;
        curr = curr->next;
        delete temp;
    }

    Head = NULL;
    NumServer = N;
    Length = L;
    Capacity = C;

    for (int i = 0; i < NumServer; ++i) {
        ServerAxis[i] = axis[i];
        ServerCnt[i] = 0;
    }
}

int add_user(int uid, int axis) {
    int maxDist = 0;
    for (int i = 0; i < NumServer; ++i) {
        int len;
        if (axis < ServerAxis[i])
            len = MIN(ServerAxis[i] - axis, Length - ServerAxis[i] + axis);
        else
            len = MIN(axis - ServerAxis[i], Length - axis + ServerAxis[i]);

        UserDistance[uid][i] = { i, len };
        maxDist = MAX(maxDist, len);
    }

    for (int i = 0; i < NumServer - 1; ++i) {
        for (int j = i + 1; j < NumServer; ++j) {
            if (UserDistance[uid][i].len > UserDistance[uid][j].len
                || (UserDistance[uid][i].len == UserDistance[uid][j].len && UserDistance[uid][i].sid > UserDistance[uid][j].sid)
                    ) {
                Distance tmp = UserDistance[uid][i];
                UserDistance[uid][i] = UserDistance[uid][j];
                UserDistance[uid][j] = tmp;
            }
        }
    }

    insert(uid, maxDist);

    for (int i = 0; i < NumServer; ++i)
        ServerCnt[i] = 0;

    traversal();

    return UserSid[uid];
}

int remove_user(int uid) {
    int ret = UserSid[uid];

    remove(UserNode[uid]);

    for (int i = 0; i < NumServer; ++i)
        ServerCnt[i] = 0;

    traversal();

    return ret;
}

int get_users(int sid) {
    return ServerCnt[sid];
}
