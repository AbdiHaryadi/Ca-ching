# Ca-ching
Ca-ching adalah *bot* yang kami buat untuk permainan Entellect Challenge 2019. *Bot* ini dibuat untuk memenuhi tugas Strategi Algoritma Semester 2 2020/2021. Ca-ching dibuat dengan menerapkan algoritma *greedy*, yaitu algoritma yang dapat dikatakan "terbaik untuk setiap giliran."

## Perencanaan
Ca-ching menggunakan algoritma *greedy* yang bernama Formation and Defense. Maka dari itu, ada dua tahap greedy: Formation dan Defense. Dalam tahap Formation, cacing-cacing kami akan berjalan ke *fixed point* yang telah kami tentukan dengan jalur secepat mungkin. Ini membuat cacing-cacing kami tidak khawatir terhadap lava. Jika cacing kami berada pada *fixed point*, dia akan berada dalam tahap Defense dan melakukan penyerangan pada cacing musuh terdekat tanpa berpindah dari tempatnya.

## Persyaratan
Sebelum menggunakan *bot* ini, berikut adalah hal-hal yang perlu disediakan sebelumnya:
- Sistem operasi Windows atau Linux (memungkinkan untuk sistem operasi selain itu, tetapi dalam tahap 6 dalam [Penggunaan](#Penggunaan), perlu ada cara khusus yang tidak dituliskan)
- Java Development Kit 8 (JDK 8)
- Entelect Challenge 2019 Worms `starter-pack.zip` (diunduh dari `https://github.com/EntelectChallenge/2019-Worms/releases`)

## Penggunaan
Ca-ching dapat menghadiri pertarungan dengan cara sebagai berikut:
1. Atur atau ubah versi Java yang digunakan menjadi JDK 8. Setiap sistem operasi memiliki cara yang berbeda-beda.
2. Ekstrak `starter-pack.zip`.
3. Salin folder `src` dari repositori ini ke folder hasil ekstrak sebelumnya.*
4. Ubah nilai `player-a` dari `game-runner-config.json` di folder `starter-pack` menjadi `./src/java`. 
5. Jika memiliki bot lain dengan bahasa pemrograman Java untuk dipertarungkan, ubah nilai `player-b` ke direktori tempat `bot.json` berada.
6. Jalankan `run.bat` untuk Windows atau `make run` untuk Linux.

*) Jika folder `src` ditempatkan pada tempat yang lain, sesuaikan nilainya berdasarkan direktori tempat bot.json-nya berada.

## Tentang
Ca-ching dibuat oleh:
- Dwianditya Hanif Raharjanto (13519046)
- Michael Owen (13519055)
- M. Abdi Haryadi. H (13519156)
