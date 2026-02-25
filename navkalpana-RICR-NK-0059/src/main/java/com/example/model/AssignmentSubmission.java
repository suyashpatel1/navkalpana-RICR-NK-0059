package com.example.model;
import jakarta.persistence.*;
import lombok.*;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submissionLink;

    private Double marks;

    private String feedback;

    private String status; // PENDING, SUBMITTED, EVALUATED

    private LocalDateTime submittedAt;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private Student student;

	public boolean isEvaluated() {
		// TODO Auto-generated method stub
		return false;
	}

	
}