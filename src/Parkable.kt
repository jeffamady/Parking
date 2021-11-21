import kotlin.math.roundToInt

open class Parkable(open var vehicle: Vehicle, open var vehicles: MutableSet<Vehicle>) {

    var countVehicle = 0
    var countMoney = 0

    fun checkOutVehicle(plate: String, onSuccess: (Int) -> String, onError: () -> String): String {
        val foundVehicleList = vehicles.map { it }.filter { it.plate == plate }
        return if (foundVehicleList.isNotEmpty()) {
            val foundVehicle = foundVehicleList[0]
            val fees = calculateFee(foundVehicle.type, foundVehicle.parkedTime, foundVehicle.discountCard != null)
            vehicles.remove(foundVehicle)
            countVehicle++
            countMoney += fees
            onSuccess(fees)
        } else onError()
    }



    private fun calculateFee(type: VehicleType, parkedTime: Long, hasDiscountCard: Boolean): Int {
        if (parkedTime > 120) {
            val blocksTime = (parkedTime - 120.0) / 15
            val roundedBlocks = blocksTime.roundToInt().run {
                if (blocksTime > this) (this + 1) else this
            }
            val totalFees = when (type) {
                VehicleType.CAR -> (roundedBlocks * 5) + VehicleType.CAR.twoHoursFees
                VehicleType.MOTO -> (roundedBlocks * 5) + VehicleType.MOTO.twoHoursFees
                VehicleType.MINIBUS -> (roundedBlocks * 5) + VehicleType.MINIBUS.twoHoursFees
                VehicleType.BUS -> (roundedBlocks * 5) + VehicleType.BUS.twoHoursFees
            }
            return if (hasDiscountCard) (totalFees * 0.85).roundToInt() else totalFees

        }
        val totalFees = when (type) {
            VehicleType.CAR -> VehicleType.CAR.twoHoursFees
            VehicleType.MOTO -> VehicleType.MOTO.twoHoursFees
            VehicleType.MINIBUS -> VehicleType.MINIBUS.twoHoursFees
            VehicleType.BUS -> VehicleType.BUS.twoHoursFees
        }
        return if (hasDiscountCard) (totalFees * 0.85).roundToInt() else totalFees
    }
}