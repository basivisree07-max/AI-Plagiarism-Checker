# ============================================================
# PLAGIARISM CHECKER - DYNAMIC CHART GENERATION
# ============================================================

# Install ggplot2 only once if it is not installed
# install.packages("ggplot2")

library(ggplot2)


# ============================================================
# FUNCTION 1: SIMILARITY METRICS CHART
# ============================================================

generate_similarity_chart <- function(data) {
  
  required_columns <- c(
    "tokenSimilarity",
    "jaccardSimilarity",
    "cosineSimilarity",
    "structuralSimilarity",
    "finalSimilarity"
  )
  
  missing_columns <- setdiff(
    required_columns,
    names(data)
  )
  
  if (length(missing_columns) > 0) {
    
    stop(
      paste(
        "Missing columns:",
        paste(missing_columns, collapse = ", ")
      )
    )
  }
  
  
  # Calculate average for each metric
  chart_data <- data.frame(
    
    Metric = c(
      "Token Similarity",
      "Jaccard Similarity",
      "Cosine Similarity",
      "Structural Similarity",
      "Final Similarity"
    ),
    
    Similarity = c(
      mean(data$tokenSimilarity, na.rm = TRUE),
      mean(data$jaccardSimilarity, na.rm = TRUE),
      mean(data$cosineSimilarity, na.rm = TRUE),
      mean(data$structuralSimilarity, na.rm = TRUE),
      mean(data$finalSimilarity, na.rm = TRUE)
    )
  )
  
  
  # Create chart
  chart <- ggplot(
    chart_data,
    aes(
      x = Metric,
      y = Similarity
    )
  ) +
    
    geom_col() +
    
    labs(
      title = "Similarity Metrics Analysis",
      x = "Similarity Metric",
      y = "Similarity (%)"
    ) +
    
    theme_minimal()
  
  
  return(chart)
}


# ============================================================
# FUNCTION 2: SUBMISSION SIMILARITY CHART
# ============================================================

generate_submission_chart <- function(data) {
  
  if (!"finalSimilarity" %in% names(data)) {
    stop("finalSimilarity column is missing.")
  }
  
  
  # Create submission numbers dynamically
  data$Submission <- paste(
    "Result",
    seq_len(nrow(data))
  )
  
  
  chart <- ggplot(
    data,
    aes(
      x = Submission,
      y = finalSimilarity
    )
  ) +
    
    geom_col() +
    
    labs(
      title = "Final Similarity by Submission Result",
      x = "Submission Result",
      y = "Final Similarity (%)"
    ) +
    
    theme_minimal()
  
  
  return(chart)
}


# ============================================================
# FUNCTION 3: RISK DISTRIBUTION CHART
# ============================================================

generate_risk_chart <- function(data) {
  
  if (!"finalSimilarity" %in% names(data)) {
    stop("finalSimilarity column is missing.")
  }
  
  
  # Calculate risk dynamically
  data$riskLevel <- ifelse(
    data$finalSimilarity >= 70,
    "HIGH",
    ifelse(
      data$finalSimilarity >= 40,
      "MEDIUM",
      "LOW"
    )
  )
  
  
  # Count each risk level
  risk_data <- as.data.frame(
    table(data$riskLevel)
  )
  
  
  names(risk_data) <- c(
    "Risk",
    "Count"
  )
  
  
  chart <- ggplot(
    risk_data,
    aes(
      x = Risk,
      y = Count
    )
  ) +
    
    geom_col() +
    
    labs(
      title = "Plagiarism Risk Distribution",
      x = "Risk Level",
      y = "Number of Results"
    ) +
    
    theme_minimal()
  
  
  return(chart)
}


# ============================================================
# FUNCTION 4: SIMILARITY DISTRIBUTION
# ============================================================

generate_distribution_chart <- function(data) {
  
  if (!"finalSimilarity" %in% names(data)) {
    stop("finalSimilarity column is missing.")
  }
  
  
  chart <- ggplot(
    data,
    aes(
      x = finalSimilarity
    )
  ) +
    
    geom_histogram(
      bins = 10
    ) +
    
    labs(
      title = "Final Similarity Distribution",
      x = "Final Similarity (%)",
      y = "Number of Results"
    ) +
    
    theme_minimal()
  
  
  return(chart)
}