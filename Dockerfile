FROM mcr.microsoft.com/playwright/java:v1.49.0-jammy

# RUN apt-get update
#RUN apt-get -install npm
# RUN chrome --disable-field-trial-config --disable-background-networking --enable-features=NetworkService,NetworkServiceInProcess --disable-background-timer-throttling --disable-backgrounding-occluded-windows --disable-back-forward-cache --disable-breakpad --disable-client-side-phishing-detection --disable-component-extensions-with-background-pages --disable-component-update --no-default-browser-check --disable-default-apps --disable-dev-shm-usage --disable-extensions --disable-features=ImprovedCookieControls,LazyFrameLoading,GlobalMediaControls,DestroyProfileOnBrowserClose,MediaRouter,DialMediaRouteProvider,AcceptCHFrame,AutoExpandDetailsElement,CertificateTransparencyComponentUpdater,AvoidUnnecessaryBeforeUnloadCheckSync,Translate --allow-pre-commit-input --disable-hang-monitor --disable-ipc-flooding-protection --disable-popup-blocking --disable-prompt-on-repost --disable-renderer-backgrounding --force-color-profile=srgb --metrics-recording-only --no-first-run --enable-automation --password-store=basic --use-mock-keychain --no-service-autorun --export-tagged-pdf --no-sandbox --user-data-dir=/tmp/playwright_chromiumdev_profile-YHRWJD --remote-debugging-pipe --no-startup-window

#ADD seccomp_profile.json ~/bin/bash/seccomp_profile.json


WORKDIR lib.usr-is-merged/projectExecution
ADD pom.xml pom.xml
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"


RUN  mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"
RUN  mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install webkit"

RUN  mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps chromium"
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install msedge"
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chromium"
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chrome"

ENV PLAYWRIGHT_BROWSERS_PATH=/ms-playwright
ADD target/docker-resources .
ENV PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=true


#ENTRYPOINT java -cp "libs/*" -Dbrowser=firefox org.testng.TestNG testSuites/RegressionTest.xml
