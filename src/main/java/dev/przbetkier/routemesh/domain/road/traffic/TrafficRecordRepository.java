package dev.przbetkier.routemesh.domain.road.traffic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRecordRepository extends MongoRepository<TrafficRecord, String> {
}
