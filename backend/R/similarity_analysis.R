# ============================================================
# DYNAMIC PLAGIARISM STATISTICAL ANALYSIS
# ============================================================

analyze_similarity <- function(data) {
  
  required_columns <- c(
    "tokenSimilarity",
    "jaccardSimilarity",
    "cosineSimilarity",
    "structuralSimilarity",
    "finalSimilarity"
  )
  
  # Check required columns
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
  
  # ----------------------------------------------------------
  # Calculate Statistics
  # ----------------------------------------------------------
  
  statistics <- data.frame(
    
    Metric = required_columns,
    
    Mean = c(
      mean(data$tokenSimilarity, na.rm = TRUE),
      mean(data$jaccardSimilarity, na.rm = TRUE),
      mean(data$cosineSimilarity, na.rm = TRUE),
      mean(data$structuralSimilarity, na.rm = TRUE),
      mean(data$finalSimilarity, na.rm = TRUE)
    ),
    
    Median = c(
      median(data$tokenSimilarity, na.rm = TRUE),
      median(data$jaccardSimilarity, na.rm = TRUE),
      median(data$cosineSimilarity, na.rm = TRUE),
      median(data$structuralSimilarity, na.rm = TRUE),
      median(data$finalSimilarity, na.rm = TRUE)
    ),
    
    Minimum = c(
      min(data$tokenSimilarity, na.rm = TRUE),
      min(data$jaccardSimilarity, na.rm = TRUE),
      min(data$cosineSimilarity, na.rm = TRUE),
      min(data$structuralSimilarity, na.rm = TRUE),
      min(data$finalSimilarity, na.rm = TRUE)
    ),
    
    Maximum = c(
      max(data$tokenSimilarity, na.rm = TRUE),
      max(data$jaccardSimilarity, na.rm = TRUE),
      max(data$cosineSimilarity, na.rm = TRUE),
      max(data$structuralSimilarity, na.rm = TRUE),
      max(data$finalSimilarity, na.rm = TRUE)
    )
  )
  
  return(statistics)
}