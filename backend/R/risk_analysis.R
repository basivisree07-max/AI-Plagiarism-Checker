# ============================================================
# DYNAMIC PLAGIARISM RISK ANALYSIS
# ============================================================

calculate_risk <- function(data) {
  
  if (!"finalSimilarity" %in% names(data)) {
    stop("finalSimilarity column is missing.")
  }
  
  # ----------------------------------------------------------
  # Determine Risk
  # ----------------------------------------------------------
  
  data$riskLevel <- ifelse(
    data$finalSimilarity >= 70,
    "HIGH",
    ifelse(
      data$finalSimilarity >= 40,
      "MEDIUM",
      "LOW"
    )
  )
  
  # ----------------------------------------------------------
  # Count Risk Levels
  # ----------------------------------------------------------
  
  high_count <- sum(
    data$riskLevel == "HIGH",
    na.rm = TRUE
  )
  
  medium_count <- sum(
    data$riskLevel == "MEDIUM",
    na.rm = TRUE
  )
  
  low_count <- sum(
    data$riskLevel == "LOW",
    na.rm = TRUE
  )
  
  # ----------------------------------------------------------
  # Return Result
  # ----------------------------------------------------------
  
  result <- list(
    
    totalSubmissions = nrow(data),
    
    highRisk = high_count,
    
    mediumRisk = medium_count,
    
    lowRisk = low_count,
    
    averageSimilarity =
      mean(data$finalSimilarity, na.rm = TRUE),
    
    medianSimilarity =
      median(data$finalSimilarity, na.rm = TRUE),
    
    minimumSimilarity =
      min(data$finalSimilarity, na.rm = TRUE),
    
    maximumSimilarity =
      max(data$finalSimilarity, na.rm = TRUE)
  )
  
  return(result)
}