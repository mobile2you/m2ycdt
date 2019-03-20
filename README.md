
# ANDROID CONDUCTOR SERVICES 

## Synopsis ##

Esse repositório contém toda a parte remota da conductor comum às aplicações. Além disso, há também classes que gerenciam dados locais persistentes e de sessão. 

## Basic Instructions ##

Para adicionar a dependência num projeto Android basta adicionar a seguinte linha no gradle:
```
dependencies {
    ...
    implementation 'org.bitbucket.m2y:m2y-conductor-services-android:0.1.2'
    ...
}
```
Versão mais recente estável: 0.1.2

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



