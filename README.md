# Android TV WebView AdBlock

Bu proje, Android TV cihazları için optimize edilmiş, yerleşik **reklam ve pop-up engelleyici** içeren bir WebView uygulamasıdır.

## Özellikler
- **Android TV & Leanback Desteği:** TV ana ekranı ve kumanda (D-pad) navigasyonu ile tam uyumlu.
- **Gelişmiş Reklam Engelleyici (AdBlocker):**
  - **Ağ Düzeyinde Engelleme (`shouldInterceptRequest`):** Popüler reklam, izleyici (tracker) ve yönlendirme alan adlarını ağ isteği seviyesinde keser ve yüklemesini engeller.
  - **DOM/CSS Injection:** Sayfa yüklendiğinde reklam alanlarını, overlay/banner katmanlarını CSS inject ederek gizler.
  - **Pop-up Koruması:** `window.open` fonksiyonunu nötralize eder ve yeni pencere/sekme açılmasını önler.
- **GitHub Actions CI/CD:** Projeyi GitHub'a pushladığınızda APK otomatik olarak derlenir ve artifacts altında indirilmeye hazır hale gelir.

## Kurulum ve Kullanım
1. Repoyu klonlayın.
2. `MainActivity.java` içerisindeki `TARGET_URL` değişkenine kendi sitenizin adresini girin.
3. GitHub reponuza pushlayın; Actions sekmesinden derlenen APK'yı indirin.
