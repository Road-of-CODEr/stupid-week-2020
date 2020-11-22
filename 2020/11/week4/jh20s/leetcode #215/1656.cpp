class OrderedStream {
public:
    vector<string> st;
    int pos = 0;
    OrderedStream(int n) {
        st.resize(n);
    }
    
    vector<string> insert(int id, string value) {
        vector<string> resp;
        st[id-1] = value;
        cout<<value<<endl;
        while (pos < st.size() && st[pos] != "") {
            resp.push_back(st[pos]);
            pos++;
        }
        return resp;
    }
};