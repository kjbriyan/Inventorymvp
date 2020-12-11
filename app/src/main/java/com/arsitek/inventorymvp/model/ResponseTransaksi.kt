package com.arsitek.inventorymvp.model

import com.google.gson.annotations.SerializedName

data class ResponseTransaksi(

	@field:SerializedName("data")
	val data: List<DataItemTransaksi>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItemTransaksi(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id_barang")
	val idBarang: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id_transaksi")
	val idTransaksi: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("pinjam")
	val pinjam: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null,

	@field:SerializedName("jumlah_barang")
	val jumlahBarang: String? = null,

	@field:SerializedName("name")
	val nama: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
