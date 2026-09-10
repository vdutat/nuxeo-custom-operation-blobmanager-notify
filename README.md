# nuxeo-custom-operation-blobmanager-notify

## Table of contents

> * [nuxeo-custom-bulk-migration](#nuxeo-custom-bulk-migration)
>   * [Table of contents](#table-of-contents)
>   * [About / Synopsis](#about--synopsis)
>   * [How to apply new rule on existing document(s)](#)
>   * [Requirements](#requirements)
>   * [Build](#build)
>   * [Installation](#installation)
>   * [Support](#support)
>   * [License](#license)
>   * [About Hyland Nuxeo](#about-hyland-nuxeo)

## About / Synopsis

This plugin demonstrates how to apply newly defined **blob dispatcher** rules on existing documents. This can be useful when defining a rule that moves blobs to a newly defined binary store, you would want to apply this new rule to existing documents so that their blobs get moved to the new binary store.

This plugin was generated with the following commands:
```
mkdir nuxeo-custom-operation-blobmanager-notify && cd $_
nuxeo bootstrap multi-module operation
nuxeo b package
mvn clean install
```

## How to apply new rule on existing document(s)

```
curl -su Administrator:Administrator -XPOST \
-H 'Content-Type:application/json' \
http://localhost:8080/nuxeo/api/v1/automation/Document.BlobManagerNotifyChanges \
-d '{
 "input": "doc:/default-domain/workspaces/pictures/picture-001", 
 "params": {"xpath": "blob:mime-type~image"},
 "context": {}
}'
```

If the rule needs to be applied on a set of documents, you can use the **Bulk Action Framework**'s REST API:
```
curl -su Administrator:Administrator -X POST \
-H 'Content-Type: application/json' \
'http://localhost:8080/nuxeo/api/v1/search/bulk/automation?scroll=elastic&query=select%20*%20from%20document%20where%20ecm:path%20startswith%20%22/default-domain/workspaces/pictures/%22' \
-d '{
  "operationId": "Document.BlobManagerNotifyChanges",
  "parameters": {
    "xpath": "blob:mime-type~image"
  }
}'
```

## Requirements

Building requires the following software:

* git
* maven

## Build

```
git clone ...
cd nuxeo-custom-operation-blobmanager-notify

mvn clean install
```

## Installation

```
nuxeoctl mp-install nuxeo-custom-operation-blobmanager-notify/nuxeo-custom-operation-blobmanager-notify-package/target/nuxeo-custom-operation-blobmanager-notify-*.zip
```

## Support

**These features are not part of the Nuxeo Production platform, they are not supported**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## About Hyland Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).


