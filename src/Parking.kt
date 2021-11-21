data class Parking(override var vehicles: MutableSet<Vehicle>, override var vehicle: Vehicle): Parkable(vehicle, vehicles) {

    private val spot = 20

    fun addVehicle(vehicle: Vehicle): String {
        return when(spot - vehicles.size) {
            in 1..spot -> {
                vehicles.add(vehicle)
                "Welcome to AlkeParking!"
            }
            else -> "Sorry, the check-in failed"
        }
    }


    fun getParkingCheckOutInfo(): String {
        val parkingCheckOutInfo: Pair<Int, Int> = Pair(countVehicle, countMoney)
        return "${parkingCheckOutInfo.first} vehicles have checked out and have earnings of $${parkingCheckOutInfo.second}."
    }

    fun listVehicles(): List<String> {
        return vehicles.map { it.plate }
    }

}