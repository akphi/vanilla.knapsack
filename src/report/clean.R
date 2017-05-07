# remove all objects and clean the console
reset.work_space <- function() {
  rm(list = ls(envir = globalenv()), envir = globalenv())
  cat("\014")
  gc()
}
# call the remove function immediately
reset.work_space()