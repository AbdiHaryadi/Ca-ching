# Ca-ching
Ca-ching adalah *bot* yang kami buat untuk permainan Entellect Challenge 2019. *Bot* ini dibuat untuk memenuhi tugas Strategi Algoritma Semester 2 2020/2021. Ca-ching dibuat dengan menerapkan algoritma *greedy*, yaitu algoritma yang dapat dikatakan "terbaik untuk setiap giliran."

## Perencanaan
Ca-ching menggunakan algoritma *greedy* yang bernama Formation and Defense. Maka dari itu, ada dua tahap greedy: Formation dan Defense. Dalam tahap Formation, cacing-cacing kami akan berjalan ke *fixed point* yang telah kami tentukan dengan jalur secepat mungkin. Ini membuat cacing-cacing kami tidak khawatir terhadap lava. Jika cacing kami berada pada *fixed point*, dia akan berada dalam tahap Defense dan melakukan penyerangan pada cacing musuh terdekat tanpa berpindah dari tempatnya.

## Persyaratan
Sebelum menggunakan *bot* ini, berikut adalah hal-hal yang perlu disediakan sebelumnya:
- Sistem operasi Windows atau Linux (memungkinkan untuk sistem operasi selain itu, tetapi dalam tahap 5 dalam [Penggunaan](#Penggunaan), perlu ada cara khusus yang tidak dituliskan)
- Java SE Development Kit 8 (unduh di [sini](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html))
- Entelect Challenge 2019 Worms `starter-pack.zip` (unduh di [sini](https://github.com/EntelectChallenge/2019-Worms/releases))

## Penggunaan
Ca-ching dapat menghadiri pertarungan dengan cara sebagai berikut:
1. Atur atau ubah versi Java yang digunakan menjadi JDK 8. Setiap sistem operasi memiliki cara yang berbeda-beda.
2. Ekstrak `starter-pack.zip`, dan letakkan foldernya pada direktori tempat repositori juga berada.
3. Ubah nilai `player-a` dari `game-runner-config.json` di folder `starter-pack` menjadi `../Ca-ching Bot/src/java`.
4. Jika memiliki bot lain dengan bahasa pemrograman Java untuk dipertarungkan, ubah nilai `player-b` ke direktori tempat `bot.json` berada. Jika tidak, biarkan nilainya.
5. Jalankan `run.bat` untuk Windows atau gunakan perintah `make run` untuk Linux.

## Tentang
Ca-ching dibuat oleh:
- Dwianditya Hanif Raharjanto (13519046)
- Michael Owen (13519055)
- M. Abdi Haryadi. H (13519156)
