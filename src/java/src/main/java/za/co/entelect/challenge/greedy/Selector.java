package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.command.*;

abstract public class Selector {
    // Mengembalikan true jika masih ada solusi selanjutnya, false jika tidak
    abstract public boolean hasNext();
    
    // Beralih ke solusi selanjutnya
    abstract public void next();
    
    // Mengembalikan solusi. Prasyarat: hasNext() telah dipanggil dan bernilai true
    abstract public Command getSolution();
    
}
