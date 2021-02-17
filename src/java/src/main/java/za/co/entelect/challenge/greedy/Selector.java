package za.co.entelect.challenge.greedy;

abstract class Selector {
    // Mengembalikan true jika masih ada solusi selanjutnya, false jika tidak
    abstract boolean hasNext();
    
    // Beralih ke solusi selanjutnya
    abstract void next();
    
    // Mengembalikan solusi. Prasyarat: hasNext() telah dipanggil dan bernilai true
    abstract Command getSolution();
    
}
