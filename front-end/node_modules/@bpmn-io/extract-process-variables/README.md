# extract-process-variables

[![Build Status](https://travis-ci.com/bpmn-io/extract-process-variables.svg?branch=master)](https://travis-ci.com/bpmn-io/extract-process-variables)

A util for [bpmn-js](https://github.com/bpmn-io/bpmn-js) to extract Camunda BPM [process variables](https://docs.camunda.org/manual/latest/user-guide/process-engine/variables/) from a BPMN 2.0 diagram.

## Installation 

```sh
$ npm i --save @bpmn-io/extract-process-variables
```

## Usage

Given [this example diagram](./test/fixtures/sub-process-own-scope.bpmn)

```js
import {
  getProcessVariables,
  getVariablesForScope,
} from '@bpmn-io/extract-process-variables';

const canvas = modeler.get('canvas');

const rootElement = canvas.getRootElement();

const allVariables = getProcessVariables(rootElement.businessObject);
/* 
  [
    {
      "name": "variable1",
      "origin": [ "Task_1" ],
      "scope": "Process_1"
    },
    {
      "name": "variable2",
      "origin": [ "Task_1" ],
      "scope": "Process_1"
    },
    {
      "name": "variable3",
      "origin": [ "Task_2" ],
      "scope": "SubProcess_1"
    }
  ]
*/

const scopeVariables = getVariablesForScope('Process_1', rootElement.businessObject);
/* 
  [
    {
      "name": "variable1",
      "origin": [ "Task_1" ],
      "scope": "Process_1"
    },
    {
      "name": "variable2",
      "origin": [ "Task_1" ],
      "scope": "Process_1"
    }
  ]
*/
```

Note that `origin` and `scope` retrieves the full moddle element. The example outputs are reduced due to better readibility.

Note that [camunda-bpmn-moddle](https://github.com/camunda/camunda-bpmn-moddle) descriptors have to be installed.

## See also

* [camunda-bpmn-moddle](https://github.com/camunda/camunda-bpmn-moddle)
* [Camunda BPM Process Variables Documentation](https://docs.camunda.org/manual/latest/user-guide/process-engine/variables/)

## License

MIT
