package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.R

data class ExtractModel(
        val date: String,
        val title: String,
        val amount: Float,
        val codigoMCC: Int?
) {
    fun getMccDrawable(): Int? {
        return when (codigoMCC) {
            1 -> R.drawable.ic_mcc_1
            2 -> R.drawable.ic_mcc_2
            3 -> R.drawable.ic_mcc_3
            4 -> R.drawable.ic_mcc_4
            5 -> R.drawable.ic_mcc_5
            6 -> R.drawable.ic_mcc_6
            7 -> R.drawable.ic_mcc_7
            8 -> R.drawable.ic_mcc_8
            9 -> R.drawable.ic_mcc_9
            10 -> R.drawable.ic_mcc_10
            11 -> R.drawable.ic_mcc_11
            12 -> R.drawable.ic_mcc_12
            13 -> R.drawable.ic_mcc_13
            14 -> R.drawable.ic_mcc_14
            15 -> R.drawable.ic_mcc_15
            16 -> R.drawable.ic_mcc_16
            17 -> R.drawable.ic_mcc_17
            18 -> R.drawable.ic_mcc_18
            19 -> R.drawable.ic_mcc_19
            20 -> R.drawable.ic_mcc_20
            21 -> R.drawable.ic_mcc_21
            22 -> R.drawable.ic_mcc_22
            23 -> R.drawable.ic_mcc_23
            24 -> R.drawable.ic_mcc_24
            25 -> R.drawable.ic_mcc_25
            26 -> R.drawable.ic_mcc_26
            27 -> R.drawable.ic_mcc_27
            28 -> R.drawable.ic_mcc_28
            29 -> R.drawable.ic_mcc_29
            30 -> R.drawable.ic_mcc_30
            31 -> R.drawable.ic_mcc_31
            32 -> R.drawable.ic_mcc_32
            33 -> R.drawable.ic_mcc_33
            34 -> R.drawable.ic_mcc_34
            35 -> R.drawable.ic_mcc_35
            36 -> R.drawable.ic_mcc_36
            37 -> R.drawable.ic_mcc_37
            38 -> R.drawable.ic_mcc_38
            39 -> R.drawable.ic_mcc_39
            40 -> R.drawable.ic_mcc_40
            41 -> R.drawable.ic_mcc_41
            42 -> R.drawable.ic_mcc_42
            43 -> R.drawable.ic_mcc_43
            44-> R.drawable.ic_mcc_44
            46 -> R.drawable.ic_mcc_46
            47 -> R.drawable.ic_mcc_47
            else -> null
        }
    }
}