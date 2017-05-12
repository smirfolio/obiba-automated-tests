# Automated Tests

1- Download [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) compatible with you're browser's
version. Place the driver in the chromeDriver directory under your home such that the full
path is `/home/<user>/chromeDriver/chromedriver`.

2- Under your `/etc/profile.d`, create an executable sh file containing the line `export WEBDRIVER=chrome`

3- Install Xvfb (`sudo apt-get install Xvfb` on ubuntu).

To setup a background screen:
```sh
Xvfb :2 -screen 0 2560x1380x24
```

To create a screenshot of the background script:
```sh
xwd -root -display :2 | xwdtopnm | pnmtojpeg > screen.jpg
```
