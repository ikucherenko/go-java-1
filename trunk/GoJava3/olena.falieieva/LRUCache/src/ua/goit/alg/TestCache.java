package ua.goit.alg;

import org.junit.*;

public class TestCache {

	LRUCache cache  = new LRUCache(5);

	@Before
	public void putIntoCache() {
		cache.set(0, 0);
		cache.set(1, 1);
		cache.set(2, 2);
		cache.set(3, 3);
		cache.set(4, 4);
	}
	@Test	
	public void getExistValue() {
		Assert.assertEquals(cache.get(2), 2);
	}
	@Test	
	public void getNotExistValue() {
		Assert.assertEquals(cache.get(6), -1);
	}
	@Test
	public void setValue_returnValue() {
		cache.set(5, 5);
		Assert.assertEquals(cache.get(5), 5);
	}
	@Test
	public void setLatestUsedValue_returnNotExistLatestValue_deletedFromHead() {
		cache.set(5, 5);
		Assert.assertEquals(cache.get(0), -1);
	}
	@Test
	public void setValueWithExistedKey_getValueWithExistedKey() {
		cache.set(3, 100500);
		Assert.assertEquals(cache.get(3), 100500);
	}	
		
	@Test
		public void getFourValues_setOneValueWithNotExistedKey_getLatestUsedValue() {
		  cache.get(1);
		  cache.get(2);
		  cache.get(3);
		  cache.get(0);
		  cache.set(7, 7);
		  Assert.assertEquals(cache.get(4), -1);
	}
}


