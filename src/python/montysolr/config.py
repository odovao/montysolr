
## Show error messages and log prints

MSBUGDEBUG = False

## Reload handler code on every run

MSKILLLOAD = False

## Default bridge that is used for projects

MSIAMHANDLER = 'montysolr.sequential_handler'

## List of modules where we load MontySolr targets

MSTARGETUS = ['montysolr.examples.twitter_test'] #'montysolr.inveniopie.targets', 'montysolr.examples.twitter_test']

# base path where fulltext files are looked for
MSFTBASEPATH = '/proj/ads/fulltext/extracted'

# use the multiprocessing version of api_calls
MSMULTIPROC = True