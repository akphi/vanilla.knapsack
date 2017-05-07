############################################################
##                      DESCRIPTION                       ##
############################################################

# configs for the scripts: directories, options, etc.

############################################################
##                       DIRECTORY                        ##
############################################################

BASE_DIR <- "../.."
DATA_DIR <- paste(BASE_DIR, "data", sep = "/")
CODE_DIR <- paste(BASE_DIR, "src", sep = "/")
REPORT_DIR <- paste(CODE_DIR, "report", sep = "/")
DOCUMENT_DIR <- paste(BASE_DIR, "document", sep = "/")


############################################################
##                      PERFORMANCE                       ##
############################################################

REPRODUCIBILITY <- TRUE
NUMBER_OF_CORE_PARALLEL <- 4
RNG_KIND <- "L'Ecuyer-CMRG"
RNG_SEED <- 0

############################################################
##                         SERVER                         ##
############################################################

HOSTS <- list(
  LOCAL = "http://127.0.0.1"
)
# keep this in a list so we can easily manage easier
PORTS <- list(
  REPORT = 2302
)

############################################################
##                       SETTINGS                         ##
############################################################

# control infos about library loading, masking of functions and conflicts 
LOADER_VERBOSE <- FALSE
LOADER_CRAN_GRAPHIC_ENABLED <- FALSE
# choose the CRAN mirror so Script can be loaded silently
# 49: USA (CA 1) [https]             50: USA (IA) [https]
# 51: USA (IN) [https]               52: USA (KS) [https]
# 53: USA (MI 1) [https]             54: USA (OR) [https]
# 55: USA (TN) [https]               56: USA (TX 1) [https]
# 57: USA (TX 2) [https]             58: (HTTP mirrors)
LOADER_CRAN_MIRROR <- 51
# control the message shown during server startup
DOCUMENT_RENDER_REMOVE_CACHE <- TRUE
DOCUMENT_RENDER_OUTPUT <- c("html", "pdf")

############################################################
##                       DISPLAY                          ##
############################################################

BACKGROUND_COLOR <- NA
# BACKGROUND_COLOR <- NA # transparent
# BACKGROUND_COLOR <- "#fdf6e3" # for presentation
XS_LENGTH <- 300
SM_LENGTH <- 500
MD_LENGTH <- 700
LG_LENGTH <- 900
XL_LENGTH <- 1100
XXL_LENGTH <- 1300
MS_LENGTH <- 1500

COLOR <- c(
  White = "#ffffff",
  "Dem. Blue" = "#232066",
  "Rep. Red" = "#E91D0E",
  Navy = "#001f3f",
  Blue = "#0074D9",
  Aqua = "#7FDBFF",
  Teal = "#39CCCC",
  Olive = "#3D9970",
  Green = "#2ECC40",
  Lime = "#01FF70",
  Yellow = "#FFDC00",
  Orange = "#FF851B",
  Red = "#FF4136",
  Maroon = "#85144b",
  Fuchsia = "#F012BE",
  Purple = "#B10DC9",
  Silver = "#DDDDDD",
  "Light Grey" = "#f1f1f1",
  Gray = "#AAAAAA",
  "Dark Grey" = "#2d2d2d",
  Black = "#111111"
)
