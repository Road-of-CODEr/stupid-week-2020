class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        string w1,w2;
        for(int i=0;i<word1.size();i++){
            w1+=word1[i];
        }
        for(int i=0;i<word2.size();i++){
            w2+=word2[i];
        }
        printf("%s\n%s",w1.c_str(),w2.c_str());
        return w1==w2?true:false;
    }
};