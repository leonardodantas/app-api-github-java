package com.github.services

import com.fasterxml.jackson.core.type.TypeReference
import com.github.jsons.Root
import com.github.operations.Filter
import com.github.operations.Order
import com.github.rest.FindRepositoriesServiceRest
import spock.lang.Specification
import utils.FileRepository

class FindRepositoriesServiceSpec extends Specification {

    def findRepositoriesServiceRest = Mock(FindRepositoriesServiceRest)
    def findRepositoriesService = new FindRepositoriesService(findRepositoriesServiceRest)

    def fileRepository = new FileRepository()

    def "shouldFindRepositories" () {
        given: "a valid username"
        def user = "leonardodantas"

        and: "a root returned when searching by username"
        def root = fileRepository.getContent("root-list", new TypeReference<List<Root>>(){})
        findRepositoriesServiceRest.execute(_ as String) >> root

        when: "run to find all repositories"
        def result = findRepositoriesService.execute(user, Filter.ACTIVE, Order.ALPHABETICAL)

        then: "the result must be different from null and equal to the expected root"
        assert result != null
        assert result == root

        and: "size is equals 23"
        assert result.size() == 23
    }


}
