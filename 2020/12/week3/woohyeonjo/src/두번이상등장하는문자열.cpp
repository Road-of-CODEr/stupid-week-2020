
#include<iostream>

#define MAX_STR_LEN 200001

typedef unsigned long long ull;

using namespace std;

ull arr[MAX_STR_LEN], sorted[MAX_STR_LEN];
char S[MAX_STR_LEN];
int T, L, ans;

void merge(ull list[], int left, int mid, int right) {
    int i, j, k, l;
    i = left;
    j = mid + 1;
    k = left;

    while (i <= mid && j <= right) {
        if (list[i] <= list[j])
            sorted[k++] = list[i++];
        else
            sorted[k++] = list[j++];
    }

    if (i > mid) {
        for (l = j; l <= right; l++)
            sorted[k++] = list[l];
    }
    else {
        for (l = i; l <= mid; l++)
            sorted[k++] = list[l];
    }

    for (l = left; l <= right; l++) {
        list[l] = sorted[l];
    }
}

void merge_sort(ull list[], int left, int right) {
    int mid;

    if (left < right) {
        mid = (left + right) / 2;
        merge_sort(list, left, mid);
        merge_sort(list, mid + 1, right);
        merge(list, left, mid, right);
    }
}

bool isPossible(int len) {
    int idx = 0;
    ull hash = 0;
    ull d = 1;

    for (int i = 0; i <= L - len; ++i) {
        if (i == 0) {
            for (int j = i + len - 1; j >= i; --j) {
                hash += S[j] * d;
                d *= 257;
            }
            //d /= 257;
        }
        else {
            hash = (hash * 257)-(S[i - 1] * d) + S[i + len - 1];
        }

        arr[idx++] = hash;
    }

    idx--;
    merge_sort(arr, 0, idx);

    for (int i = 0; i < idx - 1; ++i) {
        if (sorted[i] == sorted[i + 1]) return true;
    }

    return false;
}

int main() {
    cin >> T;

    for (int t = 1; t <= T; ++t) {
        cin >> L >> S;

        int left = 0, right = L - 1, mid = 0;
        int ans = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (isPossible(mid)) {
                ans = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        cout << "#" << t << " " << ans << endl;
    }
}

