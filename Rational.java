public class Rational {
    private long numerator;
    private long denominator;
    public Rational(int numerator, int denominator){
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        long gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    private static long gcd(long p, long q){
        long big = p >= q ? p : q;
        long small = p >= q ? q : p;
        while (small != 0){
            long mod = big % small;
            big = small;
            small = mod;
            
        }
        return big;
    }
    public Rational plus(Rational b){
        assert (Long.MAX_VALUE / Math.abs(denominator) >= Math.abs(b.denominator)) : "Overflow in addtion";
        long commonDenominator = this.denominator * b.denominator;
        long numeratorSum = this.numerator * b.denominator + b.numerator * this.denominator;
        return new Rational((int)numeratorSum,(int)commonDenominator);
    }
    public Rational minus(Rational b){
        assert (Long.MAX_VALUE / Math.abs(denominator) >= Math.abs(b.denominator)) : "Overflow in subtraction";
        long commonDenominator = this.denominator * b.denominator;
        long numeratorDifference = this.numerator * b.denominator - b.numerator * this.denominator;
        return new Rational((int)numeratorDifference, (int)commonDenominator);
    }

    public Rational times(Rational b){
        assert (Long.MAX_VALUE / Math.abs(denominator) >= Math.abs(b.denominator)) : "Overflow in multiplication";
        assert (Long.MAX_VALUE / Math.abs(numerator) >= Math.abs(b.numerator)) : "Overflow in multiplication";
        return new Rational((int)(this.numerator * b.numerator), (int)(this.denominator*b.denominator));
    }
    public Rational dividedBy(Rational b){
        assert (Long.MAX_VALUE / Math.abs(numerator) >= Math.abs(b.denominator)) : "Overflow in division";
        assert (Long.MAX_VALUE / Math.abs(b.numerator) >= Math.abs(denominator)) : "Overflow in division";
        return new Rational((int)(this.numerator * b.denominator),(int)(this.denominator*b.numerator));

    }
    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Rational that = (Rational) obj;
        return this.numerator == that.numerator && this.denominator == that.denominator;

    }
    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 3);

        System.out.println("r1 + r2 = " + r1.plus(r2));
        System.out.println("r1 - r2 = " + r1.minus(r2));
        System.out.println("r1 * r2 = " + r1.times(r2));
        System.out.println("r1 / r2 = " + r1.dividedBy(r2));
        System.out.println("r1 equals r2? " + r1.equals(r2));
    }
}
