package edu.icet.config.apidoc.event;

import edu.icet.dto.event.EventFull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(
        summary = "Add a new event",
        description = """
		This endpoint for add a new event.<br>
		The event must have either a `venueId` or a `locationId`, but not both.<br>
		If the event data is valid, it will return the full event details, or a 304 status if the event could not be added.
		""",
        requestBody = @RequestBody(
                description = "Event details to be added",
                content = @Content(
                        mediaType = "application/json",
                        examples = {
                                @ExampleObject(
                                        name = "Example 1 - Event with venueId",
                                        value = """
                {
                    "userId": 1,
                    "eventDate": "2025-04-02",
                    "startTime": "08:00:00",
                    "endTime": "12:00:00",
                    "venueId": 1,
                    "eventType": "WEDDING",
                    "capacity": 12,
                    "budgetType": "LOW",
                    "eventStatus": "SCHEDULED"
                }
                """
                                ),
                                @ExampleObject(
                                        name = "Example 2 - Event with locationId",
                                        value = """
                {
                    "userId": 1,
                    "eventDate": "2025-04-02",
                    "startTime": "08:00:00",
                    "endTime": "12:00:00",
                    "locationId": 1,
                    "eventType": "WEDDING",
                    "capacity": 12,
                    "budgetType": "LOW",
                    "eventStatus": "SCHEDULED"
                }
                """
                                )
                        }
                )
        )
)
@ApiResponse(
        responseCode = "200",
        description = "Success - Event successfully added.",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EventFull.class)
        )
)
@ApiResponse(
        responseCode = "304",
        description = "Not Modified - The event was not added due to validation or other issues. Caused errors are appended into headers with key of `errors`",
        content = @Content()
)
@ApiResponse(
        responseCode = "400",
        description = "Bad Request - The input data is invalid, such as missing required parameters.",
        content = @Content()
)
public @interface EventAddApiDoc {
}