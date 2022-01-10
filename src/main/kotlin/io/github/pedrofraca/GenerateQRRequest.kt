package io.github.pedrofraca

import com.fasterxml.jackson.annotation.JsonProperty

class GenerateQRRequest(@JsonProperty("data") val data : String,
                        @JsonProperty("width") val width: Int? = 512,
                        @JsonProperty("height") val height : Int? = 512) {
    companion object {
        const val MIN_SIZE = 512
    }
}