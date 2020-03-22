import breeze.plot._

object Binary {

    def toBinary(x: Int, bits: Int): String = {
        if (x > 0) {
            return toBinary(x / 2, bits - 1) + (x % 2).toString(); 
        }
        else {
            return "0" * bits;
        }
    }

    def weight(b: String): Int = {
        var total: Int = 0;
        b.foreach(bit => total += bit.asDigit);
        return total;
    }
}