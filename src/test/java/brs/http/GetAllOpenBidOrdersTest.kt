package brs.http

import brs.Order.Bid
import brs.assetexchange.AssetExchange
import brs.common.AbstractUnitTest
import brs.common.QuickMocker
import brs.common.QuickMocker.MockParam
import brs.http.common.Parameters.FIRST_INDEX_PARAMETER
import brs.http.common.Parameters.LAST_INDEX_PARAMETER
import brs.http.common.ResultFields.ASSET_RESPONSE
import brs.http.common.ResultFields.HEIGHT_RESPONSE
import brs.http.common.ResultFields.OPEN_ORDERS_RESPONSE
import brs.http.common.ResultFields.ORDER_RESPONSE
import brs.http.common.ResultFields.PRICE_NQT_RESPONSE
import brs.http.common.ResultFields.QUANTITY_QNT_RESPONSE
import brs.util.mustGetAsJsonObject
import brs.util.safeGetAsLong
import brs.util.safeGetAsString
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetAllOpenBidOrdersTest : AbstractUnitTest() {

    private lateinit var t: GetAllOpenBidOrders

    private lateinit var mockAssetExchange: AssetExchange

    @Before
    fun setUp() {
        mockAssetExchange = mock()

        t = GetAllOpenBidOrders(mockAssetExchange)
    }

    @Test
    fun processRequest() = runBlocking {
        val mockBidOrder = mock<Bid>()
        whenever(mockBidOrder.id).doReturn(1L)
        whenever(mockBidOrder.assetId).doReturn(2L)
        whenever(mockBidOrder.quantityQNT).doReturn(3L)
        whenever(mockBidOrder.priceNQT).doReturn(4L)
        whenever(mockBidOrder.height).doReturn(5)

        val firstIndex = 1
        val lastIndex = 2

        val mockIterator = mockCollection(mockBidOrder)
        whenever(mockAssetExchange.getAllBidOrders(eq(firstIndex), eq(lastIndex)))
                .doReturn(mockIterator)

        val result = t.processRequest(QuickMocker.httpServletRequest(
                MockParam(FIRST_INDEX_PARAMETER, "" + firstIndex),
                MockParam(LAST_INDEX_PARAMETER, "" + lastIndex)
        )) as JsonObject

        assertNotNull(result)
        val openOrdersResult = result.get(OPEN_ORDERS_RESPONSE) as JsonArray

        assertNotNull(openOrdersResult)
        assertEquals(1, openOrdersResult.size().toLong())

        val openOrderResult = openOrdersResult.get(0).mustGetAsJsonObject("openOrderResult")
        assertEquals("" + mockBidOrder.id, openOrderResult.get(ORDER_RESPONSE).safeGetAsString())
        assertEquals("" + mockBidOrder.assetId, openOrderResult.get(ASSET_RESPONSE).safeGetAsString())
        assertEquals("" + mockBidOrder.quantityQNT, openOrderResult.get(QUANTITY_QNT_RESPONSE).safeGetAsString())
        assertEquals("" + mockBidOrder.priceNQT, openOrderResult.get(PRICE_NQT_RESPONSE).safeGetAsString())
        assertEquals(mockBidOrder.height.toLong(), openOrderResult.get(HEIGHT_RESPONSE).safeGetAsLong())
    }
}
