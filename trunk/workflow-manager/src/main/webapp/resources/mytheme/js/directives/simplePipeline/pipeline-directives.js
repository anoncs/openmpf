/******************************************************************************
 * NOTICE                                                                     *
 *                                                                            *
 * This software (or technical data) was produced for the U.S. Government     *
 * under contract, and is subject to the Rights in Data-General Clause        *
 * 52.227-14, Alt. IV (DEC 2007).                                             *
 *                                                                            *
 * Copyright 2016 The MITRE Corporation. All Rights Reserved.                 *
 ******************************************************************************/

/******************************************************************************
 * Copyright 2016 The MITRE Corporation                                       *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/

'use strict';
/* globals angular, _ */

(function () {

var templateUrlPath = 'resources/js/directives';


    var module = angular.module('simplePipeline', []);

    module.directive('taskSequence', [ function () {
        return {
            templateUrl: templateUrlPath + '/simplePipeline/taskSequence.html',
            restrict: 'EA',
            scope: {
                tasks: "=",
                canEdit: "=",
                opObj: "="
            },
            link: function( scope /*, element, attrs*/ ) {
                scope.isArray = angular.isArray;

                scope.logTasks = function() {
                    console.log("tasks->");
                    console.log(scope.tasks);
                }
            }
        };
    }]);


    module.directive('mpfTask', [function () {
        return {
            templateUrl: templateUrlPath + '/simplePipeline/mpfTask.html',
            restrict: 'EA',
            scope: {
                task: "=",
                canEdit: "=",
                indexInSequence: "@",
                opObj: "="
            },
            link: function( scope /*, element, attrs*/ ) {
                scope.logTask = function() {
                    console.log("task->");
                    console.log(scope.task);
                    console.log("indexInSequence="+scope.indexInSequence)
                }
            }
        };
    }]);


    module.directive('action', [
        'ActionService', 'TaskService',
        function ( ActionService, TaskService ) {
            return {
                templateUrl: templateUrlPath + '/simplePipeline/action.html',
                restrict: 'A',
                transclude: true,
                scope: {
                    actionObj: "=?",
                    noPopover: "=?",
                    canEdit: "=?",
                    indexInSequence: "@?",
                    opObj: "=?"
                },
                link: function( scope, element, attrs ) {
                    scope.arrowIn = attrs.hasOwnProperty('arrowIn');
                    scope.showPopover = !attrs.hasOwnProperty('noPopover');
                    // arrowOut without a condition is equivalent to arrowOut="true"
                    scope.arrowOut = attrs.hasOwnProperty('arrowOut');
                    if ( scope.arrowOut && attrs.arrowOut !== "" ) {
                        attrs.$observe('arrowOut', function (value) {
                            //console.log("arrowOut=" + value);
                            scope.arrowOut = scope.$eval(value);
                        })
                    }
                    scope.canEdit = attrs.hasOwnProperty('canEdit');
                    if ( scope.canEdit && attrs.canEdit !== "" ) {
                        attrs.$observe('canEdit', function (value) {
                            //console.log("canEdit=" + value);
                            scope.canEdit = scope.$eval(value);
                        })
                    }

                    // get all available tasks
                    scope.updateAvailableTasks = function() {
                        scope.availableTasks = TaskService.query();
                    };

                    // add a task to task sequence (pipeline)
                    //  if task is null, then user wants to create a new action/task
                    scope.addTaskToPipeline = function( task ) {
                        console.log("addTaskToPipeline( task -> )");
                        console.log(task);
                        console.log("indexInSequence="+scope.indexInSequence)
                        scope.opObj.addTaskToCurrentPipeline( task.name, scope.indexInSequence );
                    };

                    // remove a task from task sequence (pipeline)
                    //  if task is null, then user wants to create a new action/task
                    scope.removeTaskFromPipeline = function( $event ) {
                        console.log("removeTaskFromPipeline( $event -> )");
                        console.log($event);
                        $event.stopPropagation();   // so it doesn't also fire popover
                        scope.opObj.removeTaskFromPipeline( scope.indexInSequence );

                    }
                }
            };
        }
    ]);

}());