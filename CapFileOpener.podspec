
  Pod::Spec.new do |s|
    s.name = 'CapFileOpener'
    s.version = '0.0.2'
    s.summary = 'This opens files with associated application on android devices'
    s.license = 'MIT'
    s.homepage = 'https://github.com/reddysuresh78/fileopener.git'
    s.author = 'Suresh Reddy'
    s.source = { :git => 'https://github.com/reddysuresh78/fileopener.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end