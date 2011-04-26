public static java.awt.Point calcPools(int numFencers, int poolSize) throws IllegalArgumentException{
		if(poolSize <= 0 || numFencers <= 0 || numFencers < poolSize) {
			throw new IllegalArgumentException("Invalid pool size or number of fencers");
		}
		if(numFencers == poolSize) {
			return new java.awt.Point(1, 0);
		}
		int curFencers = 0;
		int numBig = 0;
		int numSmall = 0;

		while(curFencers <= numFencers) {
			if(curFencers == numFencers) {
				return new java.awt.Point(numBig, numSmall);
			}

			numBig ++;
			curFencers = numBig * poolSize;
		}

		while(curFencers >= numFencers) {
			if(curFencers == numFencers) {
				if(numBig == 0)
					return new java.awt.Point(-1, -1);
				return new java.awt.Point(numBig, numSmall);
			}

			numSmall++;
			numBig--;
			curFencers = (numBig * poolSize) + (numSmall * (poolSize - 1));
		}
		// invalidPoolSize is a global constant
		//return invalidPoolSize;
		return new java.awt.Point(-1, -1);
}

