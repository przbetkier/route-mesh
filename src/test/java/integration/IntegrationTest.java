package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.przbetkier.routemesh.RouteMeshApplication;
import dev.przbetkier.routemesh.domain.admin.AdminRepository;
import dev.przbetkier.routemesh.domain.node.NodeRepository;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleRepository;
import dev.przbetkier.routemesh.domain.road.RoadRepository;
import dev.przbetkier.routemesh.domain.roundabout.RoundaboutRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.Neo4jContainer;

@SpringBootTest(classes = RouteMeshApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public abstract class IntegrationTest {

    @RegisterExtension
    private static Neo4jContainer neo4jContainer = new Neo4jContainer().withAdminPassword(null);

    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public NodeRepository nodeRepository;

    @Autowired
    public RoadRepository roadRepository;

    @Autowired
    public ObstacleRepository obstacleRepository;

    @Autowired
    public RoundaboutRepository roundaboutRepository;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    @Qualifier("customObjectMapper")
    public ObjectMapper objectMapper;

    @LocalServerPort
    protected int port;

    @BeforeAll
    public static void setUpTests() {
        startNeo4jContainer();
        System.setProperty("spring.data.neo4j.uri", neo4jContainer.getBoltUrl());
    }

    @AfterEach
    public void cleanup() {
        adminRepository.deleteAll();
        roadRepository.deleteAll();
        nodeRepository.deleteAll();
        obstacleRepository.deleteAll();
        roundaboutRepository.deleteAll();
    }

    protected String localUrl(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    private static void startNeo4jContainer() {
        if (!neo4jContainer.isRunning()) {
            neo4jContainer.start();
        }
    }
}
