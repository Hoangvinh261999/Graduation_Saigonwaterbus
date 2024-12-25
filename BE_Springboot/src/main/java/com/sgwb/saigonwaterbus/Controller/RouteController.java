package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Mapper.RouteMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.RouteDTO;
import com.sgwb.saigonwaterbus.Model.Route;
import com.sgwb.saigonwaterbus.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @GetMapping("/routes")
    public ResponseEntity<ApiResponse<Page<RouteDTO>>> getAllRoute(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        ApiResponse<Page<RouteDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<RouteDTO> routeDTOPage = routeService.findAll(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all route success");
            response.setResult(routeDTOPage);
            if(routeDTOPage.isEmpty()) {
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("No routes found");
                response.setResult(null);
                return ResponseEntity.badRequest().body(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<ApiResponse<RouteDTO>> getRouteById(@PathVariable("id") Long id) {
        ApiResponse<RouteDTO> response = new ApiResponse<>();
        try {
            RouteDTO routeDTO = routeService.findRouteById(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get route success");
            response.setResult(routeDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/route/save")
    public ResponseEntity<ApiResponse<Route>> saveRoute (@RequestBody RouteDTO routeDTO) {
        ApiResponse<Route> response = new ApiResponse<>();
        try {
            Route saveRoute = routeService.saveRouteWithWaypoints(routeDTO);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Save route success");
            response.setResult(saveRoute);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);

        }
    }

    @PutMapping("/route/update")
    public ResponseEntity<ApiResponse<Route>> updateRoute(@RequestBody RouteDTO routeDTO) {
        ApiResponse<Route> response = new ApiResponse<>();
        try {
            if (routeService.updateRouteWithWaypoints(routeDTO)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update route success");
                response.setResult(RouteMapper.INSTANCE.toRoute(routeService.findRouteById(routeDTO.getId())));
            } else {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update route failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(1000);
            e.getMessage();
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/route/delete/{id}")
    public ResponseEntity<ApiResponse<RouteDTO>> softDeleteRoute(@PathVariable("id") Long id) {
        ApiResponse<RouteDTO> response = new ApiResponse<>();
        try {
            if (routeService.deleteRouteById(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete route success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete route failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
