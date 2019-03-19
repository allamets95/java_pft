package lessons1.homework2;

import lesson1.homework2.Primes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {

    @Test
    public void testPrimes(){
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test( enabled = false)
    public void testPrimesLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrimes(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
