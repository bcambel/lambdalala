# lambdalala

A Sample project to run lambda functions in AWS.

## Installation

`lein repl` as usual

## Usage

Create a Lambda function in AWS with the name `do-something-important`. Once the Lambda is created, you are good to go to deploy the versions of the Lambda function.

/deploy_artifact.sh target/uberjar/lambdalala-0.1.0-standalone.jar default do-something-important


## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
