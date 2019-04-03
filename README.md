
# ANDROID CONDUCTOR SERVICES 

## Synopsis ##

Esse repositório contém toda a parte remota da conductor comum às aplicações. Além disso, há também classes que gerenciam dados locais persistentes e de sessão. 

## Basic Instructions ##

Para adicionar a dependência num projeto Android é necessário adicionar suporte ao jitpack no projeto, colocando a seguinte declaração no gradle do projeto:
```
allprojects {
    repositories {
        ...
        maven {
            url "https://jitpack.io"
            credentials { username authToken }
        }
        ...
    }
}
```
A credencial tem que ser colocada no arquivo gradle.properties:
```
authToken=jp_c80pndqa0tlriv8mnpov4qom3n
```
E agora basta adicionar a seguinte linha no gradle do app:
```
dependencies {
    ...
    implementation 'com.github.mobile2you:m2ycdt:0.1.0'
    ...
}
```
Para inicializar o ambiente é necessário chamar esses métodos (de preferência na BaseApplication):
```
M2YCDTPreferencesHelper.init(context)
M2YCDTEnvironment.init(baseUrl, appHeader, isDebug, shouldReportCrash)
```
Versão mais recente estável: 0.1.0

## Repositories ##
M2YCDTAccountRepository

M2YCDTBankRepository

M2YCDTBankSlipRepository

M2YCDTBilletRepository

M2YCDTCardRepository

M2YCDTLoginRepository

M2YCDTPaymentRepository

M2YCDTPeerTransferRepository

M2YCDTPersonRepository

M2YCDTRechargeRepository

M2YCDTRegistrationRepository

M2YCDTTransferRepository

## API Reference ##

https://minhacarteiradigital.herokuapp.com/apidocs

## Language ##
Kotlin

## Settings ##

```
minSdkVersion = 17
targetSdkVersion = 27
compileSdkVersion = 27
buildToolsVersion = '27.0.1'
kotlinVersion = '1.2.51'
ktxVersion = '0.3'
```



