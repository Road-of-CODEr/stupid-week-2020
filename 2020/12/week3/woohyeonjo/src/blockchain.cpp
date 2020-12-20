#define MAXSERVER	5
#define IMAGESIZE	400000

#define MAXSIZE		16

#define MAXBLOCK	15015

#define MAXCHILD    20

struct Transaction {
	unsigned char exchangeid;
	short amount;
};

struct Block {
	int   hashpreblock;
	short random;
	unsigned char size;
	Transaction trans[MAXSIZE];
	
	int   hash;
	int   hit;
	bool  available;
	int   next;
	
	int   parent;
	int   childroot;
	int   siblingnext;
};

int   nb;
Block block[MAXBLOCK];

#define MOD	(1 << 16)

int htable[MOD];

extern int calcHash(unsigned char buf[], int pos, int size);

int get(unsigned char *image, int &v) {
	v = (image[0] << 24) + (image[1] << 16) + (image[2] << 8) + image[3];
	return 4;
}

int get(unsigned char *image, short &s) {
	s = (image[0] << 8) + image[1];
	return 2;
}

int get(unsigned char *image, unsigned char &c) {
	c = image[0];
	return 1;
}

int get(unsigned char *image, Block &block) {
	int p = 0;
	
	p += get(image + p, block.hashpreblock);
	p += get(image + p, block.random);
	p += get(image + p, block.size);
	for (int i = 0; i < block.size; ++i) {
		p += get(image + p, block.trans[i].exchangeid);
		p += get(image + p, block.trans[i].amount);
	}
	
	return p;
}

inline int getblockid(int hash) {
	if (hash == 0)
		return 0;

	int b = htable[hash % MOD];
	while (b != 0) {
		if (block[b].hash == hash)
			return b;
		b = block[b].next;
	}
	
	return -1;
}

int dfs(int id, int exchangeid) {
	int ret = 0;
	
	for (int i = 0; i < block[id].size; ++i)
		if (exchangeid == block[id].trans[i].exchangeid) {
			ret += block[id].trans[i].amount;
		}

	int p = block[id].childroot;
	while (p != 0)
	{
		ret += dfs(p, exchangeid);
		p = block[p].siblingnext;
	}
	
	return ret;
}

void syncBlockChain(int S, unsigned char chainimage[MAXSERVER][IMAGESIZE]) {
	block[0].childroot = 0;
	block[0].available = true;

	nb = 1;
	
	for (int i = 0; i < MOD; ++i)
		htable[i] = 0;

	for (int s = 0; s < S; ++s) {
		int len, hash, p = 0;
		p += get(chainimage[s] + p, len);
		while(p < len + 4) {
			int pp = p;
			p += get(chainimage[s] + p, block[nb]);
			block[nb].hash = calcHash(chainimage[s], pp, p - pp);
			int id = getblockid(block[nb].hash);
			if (id == -1) {
				int h = block[nb].hash % MOD;
				block[nb].next = htable[h];
				htable[h] = nb;
				block[nb].hit = 1;
				block[nb].childroot = 0;
				++nb;
			} else {
				++block[id].hit;
			}
		}
	}
	
	for (int i = 1; i < nb; ++i)
		block[i].available = block[i].hit > S / 2;
	
	for (int i = 1; i < nb; ++i)
		if (block[i].available) {
			block[i].parent = getblockid(block[i].hashpreblock);
			block[i].siblingnext = block[block[i].parent].childroot;
			block[block[i].parent].childroot = i;
		}
}

int calcAmount(int hash, int exchangeid) {
	int id = getblockid(hash);

	return dfs(id, exchangeid);
}

