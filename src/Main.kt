import java.util.*


fun onSuccess(fees: Int) = "Your fee is $$fees. Come back soon."
fun onError() = "Sorry, the check-out failed"

fun main() {
    val car = Vehicle("AA111AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001")
    val moto = Vehicle("BB222BB", VehicleType.MOTO, Calendar.getInstance())
    val miniBUS = Vehicle("CC333CC", VehicleType.MINIBUS, Calendar.getInstance())
    val bus = Vehicle("DD444DD", VehicleType.BUS, Calendar.getInstance(), "DISCOUNT_CARD_002")
    val parking = Parking(mutableSetOf(), car)

    val vehiclesToAdd = listOf(
        car, moto, miniBUS, bus,
        Vehicle("EE555EE", VehicleType.CAR, Calendar.getInstance()),
        Vehicle("FF666FF", VehicleType.MOTO, Calendar.getInstance()),
        Vehicle("GG777GG", VehicleType.MINIBUS, Calendar.getInstance(), "DICOUNT_CARD_003"),
        Vehicle("HH888HH", VehicleType.BUS, Calendar.getInstance()),
        Vehicle("II999II", VehicleType.CAR, Calendar.getInstance()),
        Vehicle("JJ000JJ", VehicleType.MOTO, Calendar.getInstance()),
        Vehicle("KK111KK", VehicleType.MINIBUS, Calendar.getInstance()),
        Vehicle("LL222LL", VehicleType.BUS, Calendar.getInstance()),
        Vehicle("MM333MM", VehicleType.CAR, Calendar.getInstance()),
        Vehicle("NN444NN", VehicleType.CAR, Calendar.getInstance()),
        Vehicle("OO555OO", VehicleType.MINIBUS, Calendar.getInstance()),
        Vehicle("PP666PP", VehicleType.MOTO, Calendar.getInstance()),
        Vehicle("QQ777QQ", VehicleType.BUS, Calendar.getInstance()),
        Vehicle("RR888RR", VehicleType.MINIBUS, Calendar.getInstance()),
        Vehicle("SS999SS", VehicleType.CAR, Calendar.getInstance()),
        Vehicle("TT000TT", VehicleType.BUS, Calendar.getInstance()),
        Vehicle("UU111UU", VehicleType.BUS, Calendar.getInstance()),
        Vehicle("VV222VV", VehicleType.BUS, Calendar.getInstance()),
    )
    //Adding vehicules and check-in
    vehiclesToAdd.map {
        parking.addVehicle(it).also(::println)
    }

    //Check out, calculating fees and removing vehicules
    //Vehicle 1 (car) with discount card - success
    parking.checkOutVehicle(car.plate, ::onSuccess, ::onError).also(::println)

    //Vehicle 2 (moto) - success
    parking.checkOutVehicle("BB222BB", ::onSuccess, ::onError).also(::println)

    //Vehicle 3 (minibus) - error
    parking.checkOutVehicle("CC33", ::onSuccess, ::onError).also(::println)

    //Vehicle 4 (bus) with discount card - success
    parking.checkOutVehicle(bus.plate, ::onSuccess, ::onError).also(::println)

    //Get parking info
    parking.getParkingCheckOutInfo().also(::println)

    //Print the list of vehicles
    val listVehicle = parking.listVehicles()
    println(listVehicle)


}

