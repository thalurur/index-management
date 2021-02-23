package com.amazon.opendistroforelasticsearch.indexmanagement.transform.action

import com.amazon.opendistroforelasticsearch.indexmanagement.transform.action.index.IndexTransformResponse
import com.amazon.opendistroforelasticsearch.indexmanagement.transform.buildStreamInputForTransforms
import com.amazon.opendistroforelasticsearch.indexmanagement.transform.randomTransform
import org.elasticsearch.common.io.stream.BytesStreamOutput
import org.elasticsearch.rest.RestStatus
import org.elasticsearch.test.ESTestCase

class ResponseTests : ESTestCase() {

    fun `test index transform response`() {
        val transform = randomTransform()
        val res = IndexTransformResponse("someid", 1L, 2L, 3L, RestStatus.OK, transform)
        val out = BytesStreamOutput().apply { res.writeTo(this) }
        val streamedRes = IndexTransformResponse(buildStreamInputForTransforms(out))
        assertEquals("someid", streamedRes.id)
        assertEquals(1L, streamedRes.version)
        assertEquals(2L, streamedRes.seqNo)
        assertEquals(3L, streamedRes.primaryTerm)
        assertEquals(RestStatus.OK, streamedRes.status)
        assertEquals(transform, streamedRes.transform)
    }
}
