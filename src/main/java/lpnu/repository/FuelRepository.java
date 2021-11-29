package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Fuel;
import lpnu.entity.User;
import lpnu.entity.enumeration.FuelState;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FuelRepository {
    private List<Fuel> fuelList;

    private long id = 1;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("fuel.txt");
        try {
            final String savedFuelsAsString = Files.readString(file, StandardCharsets.UTF_16);
            fuelList = JacksonUtil.deserialize(savedFuelsAsString, new TypeReference<List<Fuel>>() {});

            if (fuelList == null) {
                fuelList = new ArrayList<>();
                return;
            }

            final long maxId = fuelList.stream().mapToLong(Fuel::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            fuelList = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("fuel.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(fuelList), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }
    public List<Fuel> getAllFuel() {
        return fuelList.stream().filter(e -> e.getFuelState() == FuelState.ACTIVE).collect(Collectors.toList());
    }

    public List<Fuel> getAllFuelForManager() {
        return new ArrayList<>(fuelList);
    }


    public Fuel getFuelById(final long id) {
        return fuelList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "fuel with id {" + id + "} not found"));
    }


    public Fuel getFuelByName(final String name) {
        return fuelList.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "fuel with name {" + name + "} not found"));
    }

    public Fuel saveFuel(final Fuel fuel) {
        fuel.setId(id);

        ++id;

        fuelList.add(fuel);
        return fuel;
    }

    public Fuel updateFuel(final Fuel fuel) {

        final Fuel savedFuel = getFuelById(fuel.getId());

        savedFuel.setName(fuel.getName());
        savedFuel.setPriceBuy(fuel.getPriceBuy());
        savedFuel.setPriceSell(fuel.getPriceSell());
        savedFuel.setFuelState(fuel.getFuelState());

        return savedFuel;
    }

//    public void deleteFuelById(final long id) {
//        fuelList = fuelList.stream()
//                .filter(e -> e.getId() != id)
//                .collect(Collectors.toList());
//    }
}
