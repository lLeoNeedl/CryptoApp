package com.demo.cryptoapp.data

import com.demo.cryptoapp.data.api.CoinPriceInfoDTO
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfo

class CoinInfoMapper {

    fun mapCoinPriceInfoToDTO(coinPriceInfo: CoinPriceInfo) = CoinPriceInfoDTO(

        type = coinPriceInfo.type,
        market = coinPriceInfo.market,
        fromSymbol = coinPriceInfo.fromSymbol,
        toSymbol = coinPriceInfo.toSymbol,
        flags = coinPriceInfo.flags,
        price = coinPriceInfo.price,
        lastUpdate = coinPriceInfo.lastUpdate,
        lastVolume = coinPriceInfo.lastVolume,
        lastVolumeTo = coinPriceInfo.lastVolumeTo,
        lastTradeId = coinPriceInfo.lastTradeId,
        volumeDay = coinPriceInfo.volumeDay,
        volumeDayTo = coinPriceInfo.volumeDayTo,
        volume24Hour = coinPriceInfo.volume24Hour,
        volume24HourTo = coinPriceInfo.volume24HourTo,
        openDay = coinPriceInfo.openDay,
        highDay = coinPriceInfo.highDay,
        lowDay = coinPriceInfo.lowDay,
        open24Hour = coinPriceInfo.open24Hour,
        high24Hour = coinPriceInfo.high24Hour,
        low24Hour = coinPriceInfo.low24Hour,
        lastMarket = coinPriceInfo.lastMarket,
        volumeHour = coinPriceInfo.volumeHour,
        volumeHourTo = coinPriceInfo.volumeHourTo,
        openHour = coinPriceInfo.openHour,
        highHour = coinPriceInfo.highHour,
        lowHour = coinPriceInfo.lowHour,
        topTierVolume24Hour = coinPriceInfo.topTierVolume24Hour,
        topTierVolume24HourTo = coinPriceInfo.topTierVolume24HourTo,
        change24Hour = coinPriceInfo.change24Hour,
        changePCT24Hour = coinPriceInfo.changePCT24Hour,
        changeDay = coinPriceInfo.changeDay,
        changePCTDay = coinPriceInfo.changePCTDay,
        supply = coinPriceInfo.supply,
        mktCap = coinPriceInfo.mktCap,
        totalVolume24Hour = coinPriceInfo.totalVolume24Hour,
        totalVolume24HourTo = coinPriceInfo.totalVolume24HourTo,
        totalTopTierVolume24Hour = coinPriceInfo.totalTopTierVolume24Hour,
        totalTopTierVolume24HourTo = coinPriceInfo.totalTopTierVolume24HourTo,
        imageUrl = coinPriceInfo.imageUrl,

}