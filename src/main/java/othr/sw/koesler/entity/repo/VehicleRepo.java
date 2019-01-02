package othr.sw.koesler.entity.repo;

import othr.sw.koesler.entity.Vehicle;
import othr.sw.koesler.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class VehicleRepo extends SingleIdEntityRepository<Long, Vehicle> {
}
