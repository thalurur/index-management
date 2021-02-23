package com.amazon.opendistroforelasticsearch.indexmanagement.transform.action.get

import com.amazon.opendistroforelasticsearch.indexmanagement.transform.model.Transform
import org.elasticsearch.action.ActionResponse
import org.elasticsearch.common.io.stream.StreamInput
import org.elasticsearch.common.io.stream.StreamOutput
import org.elasticsearch.common.xcontent.ToXContent
import org.elasticsearch.common.xcontent.ToXContentObject
import org.elasticsearch.common.xcontent.XContentBuilder
import org.elasticsearch.rest.RestStatus
import java.io.IOException

class GetTransformResponse : ActionResponse, ToXContentObject {
    var id: String
    var version: Long
    var seqNo: Long
    var primaryTerm: Long
    val status: RestStatus
    val transform: Transform?

    constructor(
        id: String,
        version: Long,
        seqNo: Long,
        primaryTerm: Long,
        status: RestStatus,
        transform: Transform?
    ) : super() {
        this.id = id
        this.version = version
        this.seqNo = seqNo
        this.primaryTerm = primaryTerm
        this.status = status
        this.transform = transform
    }

    @Throws(IOException::class)
    constructor(sin: StreamInput) : this(
        id = sin.readString(),
        version = sin.readLong(),
        seqNo = sin.readLong(),
        primaryTerm = sin.readLong(),
        status = sin.readEnum(RestStatus::class.java),
        transform = if (sin.readBoolean()) Transform(sin) else null
    )

    override fun writeTo(out: StreamOutput) {
        TODO("not implemented")
    }

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        TODO("not implemented")
    }
}
