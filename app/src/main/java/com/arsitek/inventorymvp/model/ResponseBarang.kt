package com.arsitek.inventorymvp.model

import com.google.gson.annotations.SerializedName

data class ResponseBarang(

	@field:SerializedName("data")
	val data: List<DataItemBarang>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItemBarang(

	@field:SerializedName("id_barang")
	val idBarang: String? = null,

	@field:SerializedName("jumlah_barang")
	val jumlahBarang: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null
)
