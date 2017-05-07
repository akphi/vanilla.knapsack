############################################################
##                      DESCRIPTION                       ##
############################################################

# script of miscellaneous tasks

############################################################
##                        LIBRARY                         ##
############################################################

source("configs.R")
source("utils.R")

############################################################
##                         TASK                           ##
############################################################

html <- function() {
  RENDER_MODE <- "html"
  bookdown::render_book(mp(REPORT_DIR, "index.Rmd"), "bookdown::gitbook")
}

# problem with LaTeX not being able to render image of remote address
# e.g. ![sds](http://127.0.0.1:1923/result/data_analyzed/plots/all_features_scatter.png)
# problem with not being able to generate RAW HTML tage inside a document
pdf <- function() {
  RENDER_MODE <- "pdf"
  bookdown::render_book(mp(REPORT_DIR, "index.Rmd"), "bookdown::pdf_book")
}

build <- function(render_options = DOCUMENT_RENDER_OUTPUT) {
  if ("html" %in% render_options) {
    html()
  }
  source("build.R");
  if ("pdf" %in% render_options) {
    pdf()
  }
  source("build.R")
  if (DOCUMENT_RENDER_REMOVE_CACHE) {
    invisible(unlink(dir(
      path = mp(REPORT_DIR),
      pattern = "_bookdown_files",
      recursive = FALSE,
      full.names = TRUE
    ), recursive = TRUE, force = TRUE))
  }
}